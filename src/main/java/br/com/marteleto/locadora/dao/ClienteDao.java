package br.com.marteleto.locadora.dao;

import java.util.List;

import org.hibernate.Query;

import br.com.marteleto.framework.core.util.ConverterUtil;
import br.com.marteleto.framework.persistence.exception.DaoException;
import br.com.marteleto.framework.persistence.hibernate.dao.abstracts.AHibernateDao;
import br.com.marteleto.framework.persistence.hibernate.transformer.HibernateResultTransformer;
import br.com.marteleto.locadora.dao.interfaces.IClienteDao;
import br.com.marteleto.locadora.filtro.PadraoFiltro;
import br.com.marteleto.locadora.vo.ClienteVO;

@SuppressWarnings({ "unchecked"})
public class ClienteDao extends AHibernateDao implements IClienteDao {
	private static final long serialVersionUID = 1L;
	
	private Query recuperarBase(String projecao, PadraoFiltro filtro) throws Exception {
		StringBuffer SQL = new StringBuffer();
		SQL.append(" SELECT");
		SQL.append("	" + projecao);
		SQL.append(" FROM");
		SQL.append("	TB_CLIENTE CLIE");
		SQL.append(" WHERE 1=1");
		if (filtro != null) {
			if (filtro.getId() != null) {
				SQL.append(" 	AND CLIE.SQ_CLIE_CLIENTE = :id");
			} else {
				if (filtro.getText() != null && !"".equals(filtro.getText().trim())) {
					SQL.append(" 	AND (");
					SQL.append(" 		   UPPER(CLIE.NM_CLIE_NOME) LIKE UPPER(:texto)");
					SQL.append(" 		OR UPPER(CLIE.NM_CLIE_EMAIL) LIKE UPPER(:texto)");
					SQL.append(" 	)");
				}
				if (filtro.getSortField() != null) {
					SQL.append(" ORDER BY " + filtro.getSortField());
					if (filtro.getSortOrder() != null) {
						SQL.append(" " + filtro.getSortOrder());
					}
				}
			}
		}
		Query query = this.getSession().createSQLQuery(SQL.toString());
		if (filtro != null) {
			if (filtro.getId() != null) {
				query.setLong("id", filtro.getId());
			} else {
				if (filtro.getFirst() != null) {
					query.setFirstResult(filtro.getFirst());
				}
				if (filtro.getPageSize() != null) {
					query.setMaxResults(filtro.getPageSize());
				}
				if (filtro.getText() != null && !"".equals(filtro.getText().trim())) {
					query.setString("texto", "%" + filtro.getText() + "%");
				}
			}
		}
		return query;
	}

	@Override
	public ClienteVO recuperarClientePorFiltro(PadraoFiltro filtro) throws DaoException {
		try {
			if(filtro != null) {
				filtro.setFirst(null);
				filtro.setPageSize(null);
				filtro.setSortOrder(null);
				filtro.setSortField(null);
			}
			StringBuffer SQL = new StringBuffer();
			SQL.append("	CLIE.SQ_CLIE_CLIENTE as \"id\",");
			SQL.append("	CLIE.NM_CLIE_NOME as \"nome\",");
			SQL.append("	CLIE.NM_CLIE_EMAIL as \"email\"");
			Query query = this.recuperarBase(SQL.toString(), filtro);
			query.setResultTransformer(HibernateResultTransformer.aliasToBean(ClienteVO.class));
			return (ClienteVO) query.uniqueResult();
		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}

	@Override
	public List<ClienteVO> buscarClientePorFiltro(PadraoFiltro filtro) throws DaoException {
		try {
			StringBuffer SQL = new StringBuffer();
			SQL.append("	CLIE.SQ_CLIE_CLIENTE as \"id\",");
			SQL.append("	CLIE.NM_CLIE_NOME as \"nome\",");
			SQL.append("	CLIE.NM_CLIE_EMAIL as \"email\"");
			Query query = this.recuperarBase(SQL.toString(), filtro);
			query.setResultTransformer(HibernateResultTransformer.aliasToBean(ClienteVO.class));
			return query.list();
		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}

	@Override
	public Integer contarClientePorFiltro(PadraoFiltro filtro) throws DaoException {
		try {
			if(filtro != null) {
				filtro.setFirst(null);
				filtro.setPageSize(null);
				filtro.setSortOrder(null);
				filtro.setSortField(null);
			}
			Query query = this.recuperarBase("count(CLIE.SQ_CLIE_CLIENTE)", filtro);
			return ConverterUtil.convert(query.uniqueResult(), Integer.class);
		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}
}