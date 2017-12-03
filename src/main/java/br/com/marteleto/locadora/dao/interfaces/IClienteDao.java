package br.com.marteleto.locadora.dao.interfaces;

import java.util.List;

import br.com.marteleto.framework.persistence.dao.interfaces.IDao;
import br.com.marteleto.framework.persistence.exception.DaoException;
import br.com.marteleto.locadora.filtro.PadraoFiltro;
import br.com.marteleto.locadora.vo.ClienteVO;

public interface IClienteDao extends IDao {
	ClienteVO recuperarClientePorFiltro(PadraoFiltro filtro) throws DaoException;
	List<ClienteVO> buscarClientePorFiltro(PadraoFiltro filtro) throws DaoException;
	Integer contarClientePorFiltro(PadraoFiltro filtro) throws DaoException;
}