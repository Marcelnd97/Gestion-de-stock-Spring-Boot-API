package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.MouveStockDto;
import com.damo.gestionDeStock.exception.EntityNotFoundException;
import com.damo.gestionDeStock.exception.ErrorCodes;
import com.damo.gestionDeStock.exception.InvalidEntityException;
import com.damo.gestionDeStock.model.MouveStock;
import com.damo.gestionDeStock.repository.MouveStkRepository;
import com.damo.gestionDeStock.service.MouveStkService;
import com.damo.gestionDeStock.validator.MouveStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MouveStkServiceImpl implements MouveStkService {


    private MouveStkRepository mouveStkRepository;

    @Autowired
    public MouveStkServiceImpl(MouveStkRepository mouveStkRepository) {
        this.mouveStkRepository = mouveStkRepository;
    }

    @Override
    public MouveStockDto save(MouveStockDto mouveStk) {

        List<String> errors = MouveStockValidator.validator(mouveStk);

        if (!errors.isEmpty()){
            log.error("Mouve Stock is not valid");
            throw new InvalidEntityException("Le mouvement de stock n'est pas valide {}",
                    ErrorCodes.MOUVE_STK_NOT_FOUND);
        }
        return MouveStockDto.fromEntity(mouveStkRepository.save(MouveStockDto.toEntity(mouveStk)));
    }

    @Override
    public MouveStockDto findById(Integer id) {

        if (id == null){
            log.error("Mouve Stock ID is not valid");
            return  null;
        }

        Optional<MouveStock> mouveStock = mouveStkRepository.findById(id);

        return Optional.of(MouveStockDto.fromEntity(mouveStock.get())).orElseThrow(()->
                new EntityNotFoundException("Aucun mouvement stock avec l'ID =" + id + "n'est trouver dans la BDD",
                        ErrorCodes.MOUVE_STK_NOT_FOUND));
    }

    @Override
    public List<MouveStockDto> findAll() {
        return mouveStkRepository.findAll().stream()
                .map(MouveStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        mouveStkRepository.deleteById(id);

    }
}
