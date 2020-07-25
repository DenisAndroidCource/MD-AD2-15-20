package by.it.academy.layoutexample.patternadapter;

public class MicroSDCard {
    private byte[] data;

    public MicroSDCard(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
