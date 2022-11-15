package com.univates.vitaldonationapi.domain.services;

import com.univates.vitaldonationapi.domain.entity.Donation;
import com.univates.vitaldonationapi.domain.exception.model.NotFoundException;
import com.univates.vitaldonationapi.domain.repository.DonationRepository;
import com.univates.vitaldonationapi.helper.PropertyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    public Donation findById(UUID id) {
        return donationRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public Donation create(Donation donation) {
        return donationRepository.save(donation);
    }

    public Donation update(Donation inDonation) {
        var donation = findById(inDonation.getId());
        PropertyHelper.copy(inDonation, donation);
        return donationRepository.save(donation);
    }

    public void delete(UUID id) {
        donationRepository.delete(findById(id));
    }

    public void delete(Donation donation) {
        donationRepository.delete(donation);
    }

}
