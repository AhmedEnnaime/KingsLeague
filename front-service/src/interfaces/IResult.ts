import IMatch from "./IMatch";
import ITeam from "./ITeam";

interface IResult {
    id?: number;
    score: string;
    winner?: ITeam;
    teamId: number;
    match: IMatch;
    createdAt?: Date;
    updatedAt?: Date;
}

export default IResult;