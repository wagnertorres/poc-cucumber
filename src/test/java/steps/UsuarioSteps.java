package steps;

import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import support.api.UsuarioApi;
import support.domain.Usuario;

import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioSteps {

    private Usuario usuarioEsperado;
    private UsuarioApi usuarioApi;

    public UsuarioSteps(){
        usuarioApi = new UsuarioApi();
        usuarioEsperado = Usuario.builder().build();
    }

    @Quando("crio um usuário")
    public void crio_um_usuário() {
        usuarioApi.criarUsuario(usuarioEsperado);
    }

    @Então("o usuário é salvo com sucesso")
    public void o_usuário_é_salvo_com_sucesso() {
        String usuarioAtual = usuarioApi.obterNomeUsuario(usuarioEsperado);
        assertThat(usuarioAtual).isEqualTo(usuarioEsperado.getUsername());
    }
}