package Hack3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Auction {
    public static void main(String[] args) {
        OnlineAuctionSystem auctionSystem = new OnlineAuctionSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Online Auction System");
            System.out.println("1. Add Auction Item");
            System.out.println("2. Place a Bid");
            System.out.println("3. View All Auction Items");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter starting bid: ");
                    double startingBid = scanner.nextDouble();
                    auctionSystem.addItem(itemName, startingBid);
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String bidItemName = scanner.nextLine();
                    while(!auctionSystem.checkitem(bidItemName)){
                        System.out.println(bidItemName +"Not found.");
                        System.out.println("Please Enter the correct item name : ");
                        bidItemName = scanner.nextLine();
                    }
                    System.out.print("Enter your name: ");
                    String bidderName = scanner.nextLine();
                    System.out.print("Enter your bid: ");
                    double bidAmount = scanner.nextDouble();
                    auctionSystem.placeBid(bidItemName, bidderName, bidAmount);
                    break;
                case 3:
                    auctionSystem.displayAllItems();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class AuctionItem {
    private String itemName;
    private double startingBid;
    private double highestBid;
    private String highestBidder;

    public AuctionItem(String itemName, double startingBid) {
        this.itemName = itemName;
        this.startingBid = startingBid;
        this.highestBid = startingBid;
        this.highestBidder = "No bids yet";
    }

    public String getItemName() {
        return itemName;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public boolean placeBid(String bidderName, double bidAmount) {
        if (bidAmount > highestBid) {
            highestBid = bidAmount;
            highestBidder = bidderName;
            return true;
        }
        return false;
    }

    public void displayItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Current Highest Bid: Rs." + highestBid);
        System.out.println("Highest Bidder: " + highestBidder);
    }
}

class OnlineAuctionSystem {
    private List<AuctionItem> auctionItems;

    public OnlineAuctionSystem() {
        auctionItems = new ArrayList<>();
    }

    public void addItem(String itemName, double startingBid) {
        auctionItems.add(new AuctionItem(itemName, startingBid));
        System.out.println("Item '" + itemName + "' added with starting bid Rs." + startingBid);
    }

    public void placeBid(String itemName, String bidderName, double bidAmount) {
        for (AuctionItem item : auctionItems) {
            if(item.getItemName().equalsIgnoreCase(itemName)){
                if (item.placeBid(bidderName, bidAmount)) {
                    System.out.println("Bid placed successfully.");
                } else {
                    System.out.println("Bid too low. Current highest bid is Rs." + item.getHighestBid());
                }
            }
        }
    }

    public boolean checkitem(String itemName){
        for(AuctionItem item : auctionItems){
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void displayAllItems() {
        if (auctionItems.isEmpty()) {
            System.out.println("No items available for auction.");
        } else {
            for (AuctionItem item : auctionItems) {
                item.displayItemDetails();
                System.out.println("-------------------------");
            }
        }
    }
}



