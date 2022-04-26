package pekversicherung.Model;

import java.io.*;
import java.lang.*;
import java.util.*;

public class InsuranceContract {

    public enum ContractStatus{
        Pending,
        Approved,
        Rejected
    }

    public static class Contract{
        private String insuranceType;
        private Date startDate;
        private Date endDate;
        private ContractStatus contractStatus;
        private int contractId;
    }

}
