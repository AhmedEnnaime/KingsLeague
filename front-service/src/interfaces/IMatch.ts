import MatchStatus from "../enums/MatchStatus";
import MatchType from "../enums/MatchType";
import IMatchDay from "./IMatchDay";
import IResult from "./IResult";
import IRound from "./IRound";
import ITeam from "./ITeam";

interface IMatch {
  id?: number;
  time: string;
  status: MatchStatus;
  matchType: MatchType;
  stadiumId?: number;
  result?: IResult;
  opponentAId?: number;
  opponentBId?: number;
  teamA?: ITeam;
  teamB?: ITeam;
  matchDayId?: number;
  roundId?: number;
  matchDay?: IMatchDay;
  round?: IRound;
  createdAt?: Date;
  updatedAt?: Date;
}

export default IMatch;
