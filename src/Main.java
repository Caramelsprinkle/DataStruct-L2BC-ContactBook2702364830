import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startContactBookApp();
    }

    private static void startContactBookApp() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        LinkedList<LinkedList<String>> fullContacts = new LinkedList<>();


        while (running) {
            printMenu();
            String userOption = sc.nextLine().toUpperCase();;

            switch (userOption) {
                case "A":
                    LinkedList<String> newContact = addNewContact();
                    fullContacts.add(newContact);
                    break;
                case "D":
                    if (fullContacts.isEmpty()) {
                        System.out.println("Your contacts book is empty");
                        break;
                    }

                    Scanner searchName = new Scanner(System.in);
                    System.out.println("Enter a name to delete from contacts");
                    String nameToBeSearched = searchName.nextLine();

                    try {
                        LinkedList<String> foundContact = searchContactBy(fullContacts, nameToBeSearched, 'n');
                        fullContacts.remove(foundContact);
                    } catch (ItemNotFoundException e) {
                        System.out.println("Name not found within your contacts");
                    }

                    break;
                case "E":
                    if (fullContacts.isEmpty()) {
                        System.out.println("Your contacts book is empty");
                        break;
                    }

                    Scanner searchEmail = new Scanner(System.in);
                    System.out.println("Enter an Email to search:");
                    String emailToBeSearched = searchEmail.nextLine();

                    try {
                        System.out.println("Found Contact: " + searchContactBy(fullContacts, emailToBeSearched, 'e'));
                    } catch(ItemNotFoundException e) {
                        System.out.println("Email not found within your contacts");
                    }
                    break;
                case "P":
                    if (fullContacts.isEmpty()) {
                        System.out.println("Your contacts book is empty");
                        break;
                    }

                    Iterator<LinkedList<String>> iterator = fullContacts.iterator();

                    System.out.println("Your list of contacts:");
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    break;
                case "S":
                    if (fullContacts.isEmpty()) {
                        System.out.println("Your contacts book is empty");
                        break;
                    }

                    Scanner generalSearch = new Scanner(System.in);

                    String searchCategory = "";
                    String searchTerm;
                    boolean isValidSearchCategory = false;

                    while (!isValidSearchCategory) {
                        switch (searchCategory) {
                            case "N":
                                isValidSearchCategory = true;
                                System.out.println("Enter a name");
                                searchTerm = generalSearch.nextLine();

                                try {
                                    System.out.println("First contact with the name " + searchTerm + " " +
                                            searchContactBy(fullContacts, searchTerm, 'n'));
                                } catch (ItemNotFoundException e) {
                                    System.out.println("a contact with the given name cannot be found");
                                }
                                break;
                            case "E":
                                isValidSearchCategory = true;
                                System.out.println("Enter an Email");
                                searchTerm = generalSearch.nextLine();

                                try {
                                    System.out.println("First contact with the Email " + searchTerm +
                                            searchContactBy(fullContacts, searchTerm, 'e'));
                                } catch (ItemNotFoundException e) {
                                    System.out.println("a contact with the given Email cannot be found");
                                }
                                break;
                            case "P":
                                isValidSearchCategory = true;
                                System.out.println("Enter a phone number");
                                searchTerm = generalSearch.nextLine();

                                try {
                                    System.out.println("First contact with the phone number " + searchTerm +
                                            searchContactBy(fullContacts, searchTerm, 'p'));
                                } catch (ItemNotFoundException e) {
                                    System.out.println("a contact with the given phone number cannot be found");
                                }
                                break;
                            default:
                                System.out.println("Enter the category you would like to search\n" +
                                        "(N)ame\n(E)mail\n(P)hone Number");
                                searchCategory = generalSearch.nextLine().toUpperCase();
                        }
                    }
                    break;
                case "Q":
                    running = false;
                    break;
                default:
                    System.out.println("Incorrect Input");
                    break;
            }
        }
    }

    private static LinkedList<String> searchContactBy(LinkedList<LinkedList<String>> fullContacts, String searchTerm, char searchCategory) throws ItemNotFoundException {
        Iterator<LinkedList<String>> iteratorForAllContacts = fullContacts.iterator();
        int indexToSearch = 0;

        switch (searchCategory) {
            case 'p':
                indexToSearch = 1;
                break;
            case 'n':
                indexToSearch = 0;
                break;
            case 'e':
                indexToSearch = 2;
                break;
        }

        while (iteratorForAllContacts.hasNext()) {
            LinkedList<String> currentContact = iteratorForAllContacts.next();
            if (currentContact.get(indexToSearch).equals(searchTerm)) {
                return currentContact;
            }
        }
        throw new ItemNotFoundException();
    }

    private static LinkedList<String> addNewContact() {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> newContact = new LinkedList<>();

        System.out.println("Enter new contact's name:");
        String newContactName = sc.nextLine();

        System.out.println("Enter new contact's phone number:");
        String newContactNumber = sc.nextLine();

        System.out.println("Enter new contact's Email:");
        String newContactEmail = sc.nextLine();

        newContact.add(newContactName);
        newContact.add(newContactNumber);
        newContact.add(newContactEmail);

        return newContact;
    }

    private static void printMenu() {
        System.out.println("********************************\n" +
                            "(A)dd\n" +
                            "(D)elete\n" +
                            "(E)mail Search\n" +
                            "(P)rint List\n" +
                            "(S)earch\n" +
                            "(Q)uit\n" +
                            "********************************");
    }
}
