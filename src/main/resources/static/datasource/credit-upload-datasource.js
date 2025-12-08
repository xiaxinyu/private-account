var credit_upload_datasource = {
  banks: [
    { id: 'CCB', text: 'Construction Bank' },
    { id: 'CMB', text: 'China Merchants Bank' },
    { id: 'GDB', text: 'Guangfa Bank' }
  ],
  cardTypes: [
    { id: 'deposit', text: 'Savings Card' },
    { id: 'credit', text: 'Credit Card' }
  ],
  cards: {
    CCB: {
      deposit: [
        { id: 'CCB-D-001', text: 'CCB Deposit 001' },
        { id: 'CCB-D-002', text: 'CCB Deposit 002' }
      ],
      credit: [
        { id: 'CCB-C-001', text: 'xxx' },
        { id: 'CCB-C-002', text: 'CCB Credit 002' }
      ]
    },
    CMB: {
      deposit: [
        { id: 'CMB-001', text: 'CMB 001' },
      ]
      ,
      credit: [
        { id: 'CMB-C-001', text: 'CMB Credit 001' }
      ]
    },
    GDB: {
      deposit: [
        { id: 'GDB-001', text: 'GDB 001' }
      ],
      credit: [
        { id: 'GDB-C-001', text: 'GDB Credit 001' }
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
