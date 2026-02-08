package managers;

import models.Equipment;
import models.StaffMember;

public class InventoryReports {

    public void generateInventoryReport(Equipment[] equipmentList) {

        System.out.println("---- Inventory Report ----");

        for (int i = 0; i < equipmentList.length; i++) {

            if (equipmentList[i] != null) {
                System.out.println(equipmentList[i]);
            }
        }
    }

    public void findExpiredWarranties(Equipment[] equipmentList) {

        System.out.println("---- Expired Warranties ----");

        int index = 0;

        while (index < equipmentList.length) {

            Equipment eq = equipmentList[index];

            if (eq != null && eq.getWarrantyMonths() == 0) {
                System.out.println(eq);
            }

            index++;
        }
    }

    public void displayAssignmentsByDepartment(StaffMember[] staffList) {

        System.out.println("---- Staff Assignments ----");

        for (StaffMember staff : staffList) {

            if (staff != null) {
                System.out.println(staff);
            }
        }
    }

    public double calculateUtilisationRate(StaffMember[] staffList) {

        int totalAssigned = 0;

        for (StaffMember staff : staffList) {

            if (staff != null) {

                for (int i = 0; i < staff.getAssignedEquipmentCount(); i++) {
                    totalAssigned++;
                }
            }
        }

        return totalAssigned;
    }

    public void generateMaintenanceSchedule(Equipment[] equipmentList) {

        System.out.println("---- Maintenance Schedule ----");

        int i = 0;

        do {

            if (i < equipmentList.length && equipmentList[i] != null) {
                System.out.println("Check maintenance for: " + equipmentList[i].getName());
            }

            i++;

        } while (i < equipmentList.length);
    }
}
