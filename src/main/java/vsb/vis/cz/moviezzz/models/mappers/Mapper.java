//package vsb.vis.cz.moviezzz.models.mappers;
//
//import vsb.vis.cz.moviezzz.models.Borrowed;
//import vsb.vis.cz.moviezzz.models.Customer;
//import vsb.vis.cz.moviezzz.models.dtos.BorrowedDTO;
//import vsb.vis.cz.moviezzz.models.dtos.CustomerDTO;
//
//import java.util.stream.Collectors;
//
//public class Mapper {
//
//    public static CustomerDTO customerToDTO(Customer customer){
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(customer.getId());
//        customerDTO.setAge(customer.getAge());
//        customerDTO.setBorroweds(customer.getBorroweds().stream().map(borrowed -> borrowedToDTO(borrowed)).collect(Collectors.toList()));
//        customerDTO.setId(customer.getId());
//        customerDTO.setId(customer.getId());
//    }
//
//    private static BorrowedDTO borrowedToDTO(Borrowed borrowed) {
//        BorrowedDTO borrowedDTO = new BorrowedDTO();
//        borrowedDTO.setId(borrowed.getId());
//    }
//
//}
