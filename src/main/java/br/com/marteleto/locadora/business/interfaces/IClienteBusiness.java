package br.com.marteleto.locadora.business.interfaces;

import java.util.List;

import br.com.marteleto.framework.persistence.business.interfaces.IBusiness;
import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.locadora.filtro.PadraoFiltro;
import br.com.marteleto.locadora.vo.ClienteVO;

public interface IClienteBusiness extends IBusiness<ClienteVO> {
	List<ClienteVO> buscarClientePorFiltro(PadraoFiltro filtro) throws BusinessException;
	Integer contarClientePorFiltro(PadraoFiltro filtro) throws BusinessException;
	ClienteVO recuperarClientePorFiltro(PadraoFiltro filtro) throws BusinessException;
	void deleteClientePorId(Long id) throws BusinessException;
	ClienteVO recuperarClientePorId(Long id) throws BusinessException;
}