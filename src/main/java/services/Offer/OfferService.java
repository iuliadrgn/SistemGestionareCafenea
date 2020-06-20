package services.Offer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.Offers.CouldNotWriteOffersException;
import exceptions.Offers.OfferAlreadyExistsException;
import models.Offer;

import org.apache.commons.io.FileUtils;
import services.FileSystem.FileSystemService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class OfferService {
    public static List<Offer> offers;
    public static final Path OFFER_PATH = FileSystemService.getPathToFile("ofr", "Offers/offers.json");

    public static void loadOffersFromFile() throws IOException {

        if (!Files.exists(OFFER_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(OfferService.class.getClassLoader().getResource("Offers/offers.json")), OFFER_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        offers = objectMapper.readValue(OFFER_PATH.toFile(), new TypeReference<List<Offer>>() {
        });

    }

    public static void addOffer(String product,String price, String number, String state,String numef) throws OfferAlreadyExistsException {
        checkOfferDoesNotAlreadyExist(product);
        offers.add(new Offer(product,price,number,state,numef));
        persistOffers();
    }

    public static void checkOfferDoesNotAlreadyExist(String product) throws OfferAlreadyExistsException {
        for (Offer of: offers) {
            System.out.println("Offer: " + of);
            if (Objects.equals(product, of.getProduct()))
                throw new OfferAlreadyExistsException(product);
        }
    }


    private static void persistOffers(){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(OFFER_PATH.toFile(), offers);
        } catch (IOException e) {
            throw new CouldNotWriteOffersException();
        }
    }


}
