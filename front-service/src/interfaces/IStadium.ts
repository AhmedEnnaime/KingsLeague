import IMatch from "./IMatch";

interface IStadium {
  id?: number;
  name: string;
  location: string;
  capacity: number;
  image?: string;
  matches?: IMatch[];
  createdAt?: Date;
  updatedAt?: Date;
}

export default IStadium;
