package nts.sixblack.learnandroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    private long followId;
    private boolean status;
    private long userId;
    private String userName;
    private String userImage;
    private String email;
    //    private Date dateCreate;
    private String dateCreate;

}
