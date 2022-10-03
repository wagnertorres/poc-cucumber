package support.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {

    @Builder.Default
    private int id = 10;
    @Builder.Default
    private String username = "wtorres";
    @Builder.Default
    private String firstName = "Wagner";
    @Builder.Default
    private String lastName = "Torres";
    @Builder.Default
    private String email = "wtorres@email.com";
    @Builder.Default
    private String password = "1234";
    @Builder.Default
    private String phone = "98888888";
    @Builder.Default
    private int userStatus = 1;
}
