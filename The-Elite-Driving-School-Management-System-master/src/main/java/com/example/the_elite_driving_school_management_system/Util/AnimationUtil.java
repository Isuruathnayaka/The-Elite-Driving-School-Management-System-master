package com.example.the_elite_driving_school_management_system.Util;

import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimationUtil {
    public static void addHoverAnimation(ImageView icon) {
        // On mouse enter (zoom in)
        icon.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), icon);
            st.setToX(1.5);  // 120% size
            st.setToY(1.5);
            st.play();
        });

        // On mouse exit (zoom out back to normal)
        icon.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), icon);
            st.setToX(1.0);  // back to 100%
            st.setToY(1.0);
            st.play();
        });
    }
}
