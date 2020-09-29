package br.com.dev2j.shareapproval.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev2j.shareapproval.api.model.Upload;




@Repository
public interface UploadRepository extends JpaRepository<Upload, UUID> {

	List<Upload> findByWaitingApproval(Boolean waitingApproval);
    
}
