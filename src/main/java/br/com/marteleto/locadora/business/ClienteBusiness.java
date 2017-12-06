package br.com.marteleto.locadora.business;

import java.util.List;

import javax.inject.Inject;

import br.com.marteleto.framework.persistence.business.abstracts.ABusiness;
import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.framework.persistence.exception.DaoException;
import br.com.marteleto.locadora.business.interfaces.IClienteBusiness;
import br.com.marteleto.locadora.dao.interfaces.IClienteDao;
import br.com.marteleto.locadora.filtro.PadraoFiltro;
import br.com.marteleto.locadora.vo.ClienteVO;

public class ClienteBusiness extends ABusiness<ClienteVO> implements IClienteBusiness {
	private static final long serialVersionUID = 1L;
	@Inject private IClienteDao dao;
	
	protected IClienteDao getDao() {
		return dao;
	}

	@Override
	public List<ClienteVO> buscarClientePorFiltro(PadraoFiltro filtro) throws BusinessException {
		try {
			return this.getDao().buscarClientePorFiltro(filtro);
		} catch (DaoException ex) {
			throw new BusinessException(ex);
		}
	}
	
	public ClienteVO recuperarClientePorFiltro(PadraoFiltro filtro) throws BusinessException {
		try {
			return this.getDao().recuperarClientePorFiltro(filtro);
		} catch (DaoException ex) {
			throw new BusinessException(ex);
		}
	}

	@Override
	public Integer contarClientePorFiltro(PadraoFiltro filtro) throws BusinessException {
		try {
			return this.getDao().contarClientePorFiltro(filtro);
		} catch (DaoException ex) {
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public ClienteVO recuperarClientePorId(Long id) throws BusinessException {
		PadraoFiltro filtro = new PadraoFiltro();
		filtro.setId(id);
		return this.recuperarClientePorFiltro(filtro);
	}

	@Override
	public void deleteClientePorId(Long id) throws BusinessException {
		ClienteVO objeto = new ClienteVO();
		objeto.setId(id);
		this.delete(objeto);
	}
}