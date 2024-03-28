// types.ts

export interface Client {
    id: number;
    name: string;
    dependents: Dependent[];
  }
  
  export interface Dependent {
    id: number;
    name: string;
  }
  