var credit_upload_datasource = {
  banks: [
    { id: 'CCB', text: 'Construction Bank' },
    { id: 'CMB', text: 'China Merchants Bank' },
    { id: 'CGB', text: 'Guangfa Bank' }
  ],
  cardTypes: [
    { id: 'debit', text: 'Debit Card' },
    { id: 'credit', text: 'Credit Card' }
  ],
  cards: {
    CCB: {
      debit: [
        { id: 'CCB-D-001', text: 'CCB Debit 001' },
        { id: 'CCB-D-002', text: 'CCB Debit 002' }
      ],
      credit: [
        { id: 'CCB-C-001', text: 'xxx' },
        { id: 'CCB-C-002', text: 'CCB Credit 002' }
      ]
    },
    CMB: {
      debit: [
        { id: 'CMB-001', text: 'CMB 001' },
      ]
      ,
      credit: [
        { id: 'CMB-C-001', text: 'CMB Credit 001' }
      ]
    },
    CGB: {
      debit: [
        { id: 'CGB-001', text: 'CGB 001' }
      ],
      credit: [
        { id: 'CGB-C-001', text: 'CGB Credit 001' }
      ]
    }
  },
  getCards: function(bankId, typeId){
    if(!bankId || !typeId){ return []; }
    var bank = this.cards[bankId];
    if(!bank){ return []; }
    var list = bank[typeId];
    return list || [];
  }
};
