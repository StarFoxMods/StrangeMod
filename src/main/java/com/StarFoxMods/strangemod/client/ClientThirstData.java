package com.StarFoxMods.strangemod.client;

public class ClientThirstData {
    public static int half_filled;
    public static int filled;
    public static double round_thirst_half_filled;
    public static double round_thirst_filled;
    private static int playerThirst;

    public static void setPlayerThirst(int thirst) {
        ClientThirstData.playerThirst = thirst;
    }

    public static int getPlayerThirst_half_filled() {
        round_thirst_half_filled = (double) playerThirst / 2;
        half_filled = (int) Math.ceil(round_thirst_half_filled);
        return half_filled;
    }

    public static int getPlayerThirst_filled() {
        round_thirst_filled = (double) playerThirst / 2;
        filled = (int) Math.floor(round_thirst_filled);
        return filled;
    }
}
