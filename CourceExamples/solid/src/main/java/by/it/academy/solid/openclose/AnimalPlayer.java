package by.it.academy.solid.openclose;

import android.util.Log;

import java.util.List;

public class AnimalPlayer {
    void playRoar(List<Animal> animalList) {
        for (Animal animal: animalList) {
            playSound(animal.getRoar());
        }
    }

    private void playSound(String sound){
        Log.d("AnimalPlayer", sound);
    }
}
