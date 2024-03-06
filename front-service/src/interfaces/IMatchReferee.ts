import { MatchRefereeKey } from "../embddables/MatchRefereeKey";
import RefereeRole from "../enums/RefereeRole";
import IMatch from "./IMatch";
import IReferee from "./IReferee";

interface IMatchReferee {
    id?: MatchRefereeKey;
    match: IMatch;
    referee: IReferee;
    notes?: string;
    refereeRole: RefereeRole;
}

export default IMatchReferee;