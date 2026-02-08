import java.util.Scanner;

import models.Equipment;
import models.StaffMember;
import managers.InventoryManager;
import managers.InventoryReports;
import exceptions.*;

public class InventorySystem {

    private static Equipment[] equipmentList = new Equipment[20];
    private static StaffMember[] staffList = new StaffMember[10];

    private static int equipmentCount = 0;
    private static int staffCount = 0;

    private static InventoryManager manager = new InventoryManager();
    private static InventoryReports reports = new InventoryReports();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n===== University Inventory System =====");
            System.out.println("1. Add Equipment");
            System.out.println("2. Register Staff Member");
            System.out.println("3. Assign Equipment");
            System.out.println("4. Return Equipment");
            System.out.println("5. Search Equipment");
            System.out.println("6. Generate Reports");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        addEquipment(scanner);
                        break;

                    case 2:
                        registerStaff(scanner);
                        break;

                    case 3:
                        assignEquipment(scanner);
                        break;

                    case 4:
                        returnEquipment(scanner);
                        break;

                    case 5:
                        searchEquipment(scanner);
                        break;

                    case 6:
                        generateReports();
                        break;

                    case 7:
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid choice.");

                }

            } catch (InventoryException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 7);

        scanner.close();
    }

    private static void addEquipment(Scanner scanner) {

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Asset ID: ");
        String assetId = scanner.nextLine();

        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Warranty months: ");
        int warranty = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        equipmentList[equipmentCount++] =
                new Equipment(id, name, true, assetId, brand, warranty, category);

        System.out.println("Equipment added successfully.");
    }

    private static void registerStaff(Scanner scanner) {

        System.out.print("Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        staffList[staffCount++] = new StaffMember(id, name, email);

        System.out.println("Staff registered successfully.");
    }

    private static void assignEquipment(Scanner scanner)
            throws EquipmentNotAvailableException, AssignmentLimitExceededException {

        StaffMember staff = staffList[0];
        Equipment equipment = equipmentList[0];

        manager.assignEquipment(staff, equipment);

        System.out.println("Equipment assigned successfully.");
    }

    private static void returnEquipment(Scanner scanner)
            throws InventoryException {

        StaffMember staff = staffList[0];

        System.out.print("Enter Asset ID to return: ");
        String assetId = scanner.nextLine();

        manager.returnEquipment(staff, assetId);

        for (Equipment eq : equipmentList) {
            if (eq != null && eq.getAssetId().equals(assetId)) {
                eq.setAvailable(true);
            }
        }

        System.out.println("Equipment returned successfully.");
    }

    private static void searchEquipment(Scanner scanner) {

        System.out.print("Search by name: ");
        String name = scanner.nextLine();

        boolean found = manager.searchEquipment(name, equipmentList);

        if (found) {
            System.out.println("Equipment found.");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void generateReports() {

        reports.generateInventoryReport(equipmentList);
        reports.findExpiredWarranties(equipmentList);
        reports.displayAssignmentsByDepartment(staffList);

        double rate = reports.calculateUtilisationRate(staffList);
        System.out.println("Utilisation count: " + rate);

        reports.generateMaintenanceSchedule(equipmentList);
    }
}
