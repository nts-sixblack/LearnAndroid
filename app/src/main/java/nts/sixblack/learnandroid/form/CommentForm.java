package nts.sixblack.learnandroid.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {
    private String comment;
    private long tusId;
}
