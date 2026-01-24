package com.example.Hotels_DWBI.oltp.service;

import com.example.Hotels_DWBI.oltp.model.Payment;
import com.example.Hotels_DWBI.oltp.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
    }

    public Payment createPayment(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Integer id, Payment updatedPayment) {
        Payment payment = getPaymentById(id);

        payment.setReservation(updatedPayment.getReservation());
        payment.setAmount(updatedPayment.getAmount());
        payment.setCurrency(updatedPayment.getCurrency());
        payment.setMethod(updatedPayment.getMethod());
        payment.setStatus(updatedPayment.getStatus());
        payment.setTransactionRef(updatedPayment.getTransactionRef());

        return paymentRepository.save(payment);
    }

    public void deletePayment(Integer id) {
        Payment payment = getPaymentById(id);
        paymentRepository.delete(payment);
    }
}
