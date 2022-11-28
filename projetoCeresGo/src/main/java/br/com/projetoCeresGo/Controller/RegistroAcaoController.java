package br.com.projetoCeresGo.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoCeresGo.Models.Central;
import br.com.projetoCeresGo.Models.RegistroAcao;
import br.com.projetoCeresGo.Models.Rele;
import br.com.projetoCeresGo.Repository.CentralRepository;
import br.com.projetoCeresGo.Repository.RegistroAcaoRepository;
import br.com.projetoCeresGo.Repository.ReleRepository;

@CrossOrigin
@RestController
@RequestMapping("v1/")
public class RegistroAcaoController {

	@Autowired
	RegistroAcaoRepository registroAcaoRepository;

	@Autowired
	ReleRepository releRepository;

	@CrossOrigin
	@GetMapping("registroacao/listarTodos")
	public List<RegistroAcao> listarTodos() {

		return registroAcaoRepository.findAll();

	}

	@CrossOrigin
	@GetMapping("registroacao/listarAcoesNaoRespondidasPorCentral/{id_central}")
	public ResponseEntity listarAcoesNaoRespondidasPorCentral(@PathVariable int id_central) {

		String lista_acoes = "";

		List<RegistroAcao> acoes = registroAcaoRepository.listarAcoesNaoRespondidasPorCentral(id_central);

		if (acoes.size() > 0) {
			for (RegistroAcao acao : acoes) {
				if (acao != null) {
					String acao_formatada = "R" + acao.getId_registro() + "-" + acao.getRele_afiliado().getId_rele()
							+ "#" + acao.getRequisicao() + "@;";
					System.out.println("Acao formatada: " + acao_formatada);
					lista_acoes = lista_acoes + acao_formatada;
				}
			}

			return ResponseEntity.ok().body(lista_acoes);
		} else {
			return ResponseEntity.status(404).body("Nenhuma requisicao");
		}

	}

	@CrossOrigin
	@GetMapping("registroacao/responderRequisicao/{id_requisicao}")
	public ResponseEntity responderRequisicoes(@PathVariable int id_requisicao) {

		return registroAcaoRepository.findById(id_requisicao).map(record -> {

			record.setData_hora_resposta(LocalDateTime.now());
			record.setRespondido(1);

			RegistroAcao updated = registroAcaoRepository.save(record);

			// atualizar status do rele
			Rele rele = releRepository.findById(record.getRele_afiliado().getId_rele()).get();

			if (rele != null) {
				// atuazliar status do rele

				rele.setStatus(record.getRequisicao());

				rele = releRepository.save(rele);

			}

			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());

	}

	@CrossOrigin
	@GetMapping("registroacao/responderRequisicaoStatusReles/{resposta}")
	public boolean responderRequisicoes(@PathVariable String resposta) {

		boolean retorno = false;
		System.out.println("Resposta: " + resposta);
		String array[] = resposta.split(",");
		for (String id_status : array) {

			System.out.println("id_status: " + id_status);
			String array_id_status[] = id_status.split("-");
			int id_rele = Integer.parseInt(array_id_status[0]);
			int status = Integer.parseInt(array_id_status[1]);
			System.out.println("Id do rele: " + id_rele + " Status: " + status);

			// atualizar status do rele
			Rele rele = releRepository.findById(id_rele).get();

			if (rele != null) {
				// atuazliar status do rele

				rele.setStatus(status);

				rele = releRepository.save(rele);
				if (rele != null) {
					retorno = true;
				} else {
					retorno = false;
				}
			}

		}

		return retorno;

	}

	@CrossOrigin
	@GetMapping("registroacao/registrarAtualizarStatusReles/{id_central}")
	public boolean atualizarStatusReles(@PathVariable int id_central) {

		RegistroAcao requerer_atualizar = new RegistroAcao();
		requerer_atualizar.setId_central(id_central);
		requerer_atualizar.setRequisicao(2);
		Rele releafiliado = new Rele();
		releafiliado.setId_rele(1);
		requerer_atualizar.setRele_afiliado(releafiliado);
		requerer_atualizar.setData_hora_requisicao(LocalDateTime.now());

		return registroAcaoRepository.save(requerer_atualizar) != null;

	}

	@CrossOrigin
	@GetMapping("registroacao/acaoEmRele/{id_central}/{id_rele}/{id_acao}")
	public boolean registrarAcao(@PathVariable int id_central, @PathVariable int id_rele, @PathVariable int id_acao) {

		RegistroAcao acao = new RegistroAcao();
		acao.setId_central(id_central);
		acao.setRequisicao(id_acao);
		Rele releafiliado = new Rele();
		releafiliado.setId_rele(id_rele);
		acao.setRele_afiliado(releafiliado);
		acao.setData_hora_requisicao(LocalDateTime.now());

		return registroAcaoRepository.save(acao) != null;

	}

}
