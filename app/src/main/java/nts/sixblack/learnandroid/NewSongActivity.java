package nts.sixblack.learnandroid;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import lombok.SneakyThrows;

public class NewSongActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button btnStart;
    private Button btnStop;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_song);
        btnStart = (Button) findViewById(R.id.btnStartMusic);
        btnStop = (Button) findViewById(R.id.btnStopMusic);

        String url = "https://nts-sixblack-hexa.s3.ap-southeast-1.amazonaws.com/1648356047280";

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }
}