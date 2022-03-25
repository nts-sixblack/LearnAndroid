package nts.sixblack.learnandroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String avatar;
    private String background;
    private String email;
    private String password;
    private String name;
    private String phone;
    private boolean followStatus;
    private String token;
    private String dateCreate;

    private List userSender;
    private List userRecipient;
    private List postsList;
    private List songList;
    private List listSongInfoList;
}
