package Main;

public enum GameStates {

    PLAYING, MENU, SETTINGS;

    public static GameStates gameStates = MENU;

    public static void SetGameState(GameStates state){
        gameStates = state;
    }

}

