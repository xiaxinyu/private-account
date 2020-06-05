package com.account.domain.credit.bo;

import com.account.persist.model.CreditRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class CreditUploadBO {

    CreditRecord creditRecord;

    List<String[]> dataRows;
}
