package cui.repair.store.entity;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class LoginRecord {
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String locked;
    @NotNull
    private int times;
    private String logindate;
    public LoginRecord(){

    }
    public LoginRecord(String username,String locked,int times){
        this.username = username;
        this.locked = locked;
        this.times = times;
    }

}
