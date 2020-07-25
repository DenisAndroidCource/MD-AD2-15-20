package by.it.academy.layoutexample.patternadapter;

public class CardReader implements Usb {

    private MicroSDCard microSDCard;

    public CardReader(MicroSDCard microSDCard) {
        this.microSDCard = microSDCard;
    }

    @Override
    public byte[] readData() {
        return microSDCard.getData();
    }
}
