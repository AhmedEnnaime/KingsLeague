import ILeague from "./ILeague";
import IMatch from "./IMatch";

interface IMatchDay {
    id?: number;
    date: string;
    league?: ILeague;
    tournamentId?: number;
    matches?: IMatch[];
    createdAt?: Date;
    updatedAt?: Date;
}

export default IMatchDay;