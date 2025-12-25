package com.example.bundle_up.keymapping;

import org.lwjgl.glfw.GLFW;

public class KeyMapCommon {
    public static final CommonKey BUNDLE_UP = new CommonKey(
            "Bundle",
            GLFW.GLFW_KEY_G,
            "Bundle up"
    );

    public record CommonKey(String name, int key, String category){}
}
