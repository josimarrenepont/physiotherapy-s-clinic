const validateCPF = (cpf: string): boolean => {
    // Verifica se o CPF possui 11 dígitos
    if (cpf.length !== 11) {
      return false;
    }
  
    // Verifica se todos os dígitos são iguais
    if (/^(\d)\1{10}$/.test(cpf)) {
      return false;
    }
  
    // Calcula o primeiro dígito verificador
    let sum = 0;
    for (let i = 1; i <= 9; i++) {
      sum += parseInt(cpf[i - 1]) * (11 - i);
    }
    let mod = (sum * 10) % 11;
    if (mod === 10 || mod === 11) {
      mod = 0;
    }
    if (mod !== parseInt(cpf[9])) {
      return false;
    }
  
    // Calcula o segundo dígito verificador
    sum = 0;
    for (let i = 1; i <= 10; i++) {
      sum += parseInt(cpf[i - 1]) * (12 - i);
    }
    mod = (sum * 10) % 11;
    if (mod === 10 || mod === 11) {
      mod = 0;
    }
    if (mod !== parseInt(cpf[10])) {
      return false;
    }
  
    return true;
  };
  
  const validateRG = (rg: string): boolean => {
    // Verifica se o RG possui 9 dígitos e 1 dígito verificador
    if (rg.length !== 10) {
      return false;
    }
  
    // Extrai os dígitos do RG
    const digits = rg.substring(0, 9);
    const dv = rg.substring(9, 1);
  
    // Calcula o dígito verificador
    let sum = 0;
    for (let i = 0; i < 9; i++) {
      sum += parseInt(digits.charAt(i)) * (9 - i);
    }
    const mod = sum % 11;
    const calculatedDV = (mod < 2) ? 0 : 11 - mod;
  
    // Verifica se o dígito verificador calculado é igual ao dígito verificador do RG
    return parseInt(dv) === calculatedDV;
  };
  
  
  export { validateCPF, validateRG };
  