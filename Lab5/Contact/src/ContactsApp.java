//Корчаковский Тимофей 5 группа 1) Задача "контакты"
//а) разработать класс Контакт, определяющий запись в электронной книге мобильного
//телефона и содержащий по меньшей мере следующие поля:
//- *Наименование (имя человека или организации)
//- *Номер телефона мобильного
//- Номер телефона рабочего
//- Номер телефона (домашнего)
//- Адрес электронной почты
//- Адрес веб-страницы
//- Адрес
//
//Обязательными является поля помеченные *, остальные поля могут быть пустыми
//
//б) класс Контакт должен реализовать:
//-интерфейс Comparable и Comparator с возможностью выбора одного из полей для сравнения
//-метод для сохранения значений всех полей в строке текста (переопределить toString())
//-конструктор или метод для инициализации объекта из строки текста
//
//в) Для тестирования класса Контакт, создать консольное приложение позволяющее
//создать небольшой массив контактов и напечатать отсортированными по
//выбранному полю.
import java.util.*;

class Contact implements Comparable<Contact> {
    private String name;
    private String mobileNumber;
    private String workNumber;
    private String homeNumber;
    private String email;
    private String website;
    private String address;

    public Contact(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public Contact(String name, String mobileNumber, String workNumber, String homeNumber, String email, String webpage, String address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.email = email;
        this.website = webpage;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }


    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Mobile Number: " + mobileNumber + "\n" +
                "Work Number: " + workNumber + "\n" +
                "Home Number: " + homeNumber + "\n" +
                "Email: " + email + "\n" +
                "Website: " + website + "\n" +
                "Address: " + address + "\n";
    }

    public static Contact fromString(String contactString) {
        String[] fields = contactString.split("[\\s,.:;!?_&]+");
        if (fields.length < 2) {
            throw new IllegalArgumentException("Invalid contact string");
        }

        String name = fields[0];
        String mobileNumber = fields[1];

        Contact contact = new Contact(name, mobileNumber);

        if (fields.length > 2) {
            contact.workNumber = fields[2];
        }
        if (fields.length > 3) {
            contact.homeNumber = fields[3];
        }
        if (fields.length > 4) {
            contact.email = fields[4];
        }
        if (fields.length > 5) {
            contact.website = fields[5];
        }
        if (fields.length > 6) {
            contact.address = fields[6];
        }

        return contact;
    }
}

public class ContactsApp {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        Contact contact1 = new Contact("John Doe", "123456789", "987654321", "111111111", "john.doe@example.com", "www.example.com", "123 Main St");
        Contact contact2 = new Contact("Jane Smith", "987654321", "123456789", "222222222", "jane.smith@example.com", "www.example.com", "456 Elm St");
        Contact contact3 = new Contact("Alice Johnson", "555555555", "444444444", "333333333", "alice.johnson@example.com", "www.example.com", "789 Oak St");

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберите аспект сортировки (1.name,2.mobileNumber,3.workNumber,4.homeNumber,введите номер аспекта): ");
        String sortAspect = scanner.nextLine();

        Comparator<Contact> comparator;
        switch (sortAspect) {
            case "1":
                comparator = Comparator.comparing(Contact::getName);
                break;
            case "2":
                comparator = Comparator.comparing(Contact::getMobileNumber);
                break;
            case "3":
                comparator = Comparator.comparing(Contact::getWorkNumber);
                break;
            case "4":
                comparator = Comparator.comparing(Contact::getHomeNumber);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort aspect: " + sortAspect);
        }

        Collections.sort(contacts, comparator);

        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
