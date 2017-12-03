package br.com.marteleto.framework.persistence.hibernate.transformer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.transform.ResultTransformer;

import br.com.marteleto.framework.core.util.ConverterUtil;
import br.com.marteleto.framework.core.util.ObjectUtil;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class HibernateResultTransformer implements ResultTransformer {
	private static final long serialVersionUID = 1L;
	private transient final Logger LOG = Logger.getLogger(this.getClass().getName());
	private final Class resultClass;
	private Map<Object,Object> entities;
	private Map<String, String> alias;
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0001 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0001";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0003 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0003";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0004 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0004";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0005 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0005";
	

	public HibernateResultTransformer(Class resultClass) {
		if (resultClass == null) {
			throw new IllegalArgumentException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0001);
		}
		this.resultClass = resultClass;
		this.entities = new LinkedHashMap<Object,Object>();
	}

	public static HibernateResultTransformer aliasToBean(Class target) {
		return new HibernateResultTransformer(target);
	}

	@Override
	public List transformList(List list) {
		return new ArrayList<>(entities.values());
	}
	
	private boolean isCollection(Class clazz) {
		if (Collection.class.isAssignableFrom(clazz)) {
			return true;
		}
		return false;
	}
	
	private Collection newInstanceCollection(Class clazz) throws Exception {
		if (Set.class.isAssignableFrom(clazz)) {
			return new HashSet<>();
		}
		return new ArrayList<>();
	}

	@Override
	public Object transformTuple(Object[] values, String[] fields) {
		Object result = null;
		try {
			Field attribute = null;
			Class clazz = resultClass;
			Object id = this.getId(values, fields, null);
			result = entities.get(id);
			if (result == null) {
				result = clazz.newInstance();
				entities.put(id,result);
			}
			for (int cont = 0; cont < fields.length; cont++) {
				String field = fields[cont];
				if (this.alias != null && this.alias.containsKey(field)) {
					field = this.alias.get(field);
				}
				String[] labels = null;
				if (field.indexOf(".") != -1) {
					labels = field.split("\\.");
				} else {
					labels = new String[] { field };
				}
				Object resultTemp = result;
				String concatLabel = "";
				String separador = "";
				if (values[cont] != null) {
					for (String label : labels) {
						concatLabel = concatLabel + separador + label;
						String[] keys = { label, field };
						attribute = ObjectUtil.getFieldByName(resultTemp.getClass(), label);
						if (attribute != null) {
							attribute.setAccessible(true);
							Class returnType = attribute.getType();
							if (this.isCollection(returnType)) {
								Collection instance = (Collection) attribute.get(resultTemp);
								if (instance == null) {
									instance = this.newInstanceCollection(returnType);
									attribute.set(resultTemp, instance);
								}
								if (attribute.getGenericType() instanceof ParameterizedType) {
									ParameterizedType parameterizedType = (ParameterizedType) attribute.getGenericType();
									Type[] typeArguments = parameterizedType.getActualTypeArguments();
									if (typeArguments != null && typeArguments.length == 1) {
										resultTemp = findEntityById(instance,getId(values, fields, concatLabel));
										if (resultTemp == null) {
											resultTemp = ((Class) typeArguments[0]).newInstance();
											instance.add(resultTemp);
										}
									} else {
										throw new Exception(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002);
									}
								} else {
									throw new Exception(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002);
								}
							} else {
								try {
									if (attribute.getType().isEnum() || attribute.getType().isPrimitive() || attribute.getType().getName().startsWith("java.")) {
										Object value = values[cont];
										if (value instanceof Clob) {
											Clob clob = (Clob) value;
											value = clob.getSubString(1, (int) clob.length());
										}
										attribute.set(resultTemp, ConverterUtil.convert(value, attribute.getType()));
									} else {
										Object temp = attribute.get(resultTemp);
										if (temp == null) {
											temp = attribute.getType().newInstance();
										}
										attribute.set(resultTemp, temp);
										resultTemp = temp;
									}
								} catch (Exception ex) {
									LOG.log(Level.SEVERE, ex.getMessage(), ex);
								}
							}
						} else {
							if (label != null) {
								if (!"ROWNUM_".equals(label.trim().toUpperCase())) {
									LOG.log(Level.WARNING, "LABEL not exists: [" + label + "]");
								}
							} else {
								LOG.log(Level.SEVERE, "LABEL not exists: [" + label + "]");
							}
						}
						separador = ".";
					}
				}
			}
		} catch (Exception ex) {
			throw new HibernateException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0003, ex);
		}
		return result;
	}
	
	public void addAlias(String key, String value) {
		if (this.alias == null) {
			this.alias = new HashMap<String, String>();
		}
		this.alias.put(key, value);
	}
	
	public void removeAlias(String key) {
		if (this.alias != null && this.alias.size() > 0) {
			this.alias.remove(key);
		}
	}

	public Map<String, String> getAlias() {
		return alias;
	}

	public void setAlias(HashMap<String, String> alias) {
		this.alias = alias;
	}
	
	private Object getId(Object[] values, String[] aliases, String sufixo) throws Exception {
		if (sufixo == null) {
			sufixo = "";
		} else {
			sufixo = sufixo + ".";
		}
		for (int i = 0; i < aliases.length; i++) {
			String alias = aliases[i];
			if (this.alias != null && this.getAlias().containsKey(alias)) {
				alias = this.getAlias().get(alias);
			}
			if (alias.trim().toLowerCase()
					.equals(sufixo.trim().toLowerCase() + "id")) {
				if (values[i] != null) {
					return values[i];
				}
				return null;
			}
		}
		throw new Exception(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0004);
	}

	private Object findEntityById(Collection objects, Object id) throws Exception {
		if (objects != null && objects.size() > 0 && id != null) {
			for (Object object : objects) {
				Field attribute = ObjectUtil.getFieldByName(object.getClass(), "id");
				if (attribute != null) {
					attribute.setAccessible(true);
					Object result = attribute.get(object);
					Object tmp = id;
					if (tmp instanceof BigDecimal) {
						tmp = Long.valueOf(id.toString());
					}
					if (result != null && result.equals(tmp)) {
						return object;
					}
				} else {
					throw new Exception(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0005);
				}
			}
		}
		return null;
	}
}