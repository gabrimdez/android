// DataRepository.java
package com.example.a21rviewcomplejo;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    public static List<ItemData> obtenerAvatar() {
        ArrayList<ItemData> nameList = new ArrayList<>();
        // Cambiado: textoSuperior = "Avatar X", textoInferior = "Descripcion X"
        nameList.add(new ItemData("Avatar 1", "Descripcion 1", R.drawable.avatar_1));
        nameList.add(new ItemData("Avatar 2", "Descripcion 2", R.drawable.avatar_2));
        nameList.add(new ItemData("Avatar 3", "Descripcion 3", R.drawable.avatar_3));
        nameList.add(new ItemData("Avatar 4", "Descripcion 4", R.drawable.avatar_4));
        nameList.add(new ItemData("Avatar 5", "Descripcion 5", R.drawable.avatar_5));
        nameList.add(new ItemData("Avatar 6", "Descripcion 6", R.drawable.avatar_6));
        nameList.add(new ItemData("Avatar 7", "Descripcion 7", R.drawable.avatar_7));
        nameList.add(new ItemData("Avatar 8", "Descripcion 8", R.drawable.avatar_8));
        nameList.add(new ItemData("Avatar 9", "Descripcion 9", R.drawable.avatar_9));
        nameList.add(new ItemData("Avatar 10", "Descripcion 10", R.drawable.avatar_10));
        nameList.add(new ItemData("Avatar 11", "Descripcion 11", R.drawable.avatar_11));
        nameList.add(new ItemData("Avatar 12", "Descripcion 12", R.drawable.avatar_12));
        nameList.add(new ItemData("Avatar 13", "Descripcion 13", R.drawable.avatar_13));
        nameList.add(new ItemData("Avatar 14", "Descripcion 14", R.drawable.avatar_14));
        nameList.add(new ItemData("Avatar 15", "Descripcion 15", R.drawable.avatar_15));
        nameList.add(new ItemData("Avatar 16", "Descripcion 16", R.drawable.avatar_16));
        return nameList;
    }
}
