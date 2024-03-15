import IMatchDay from "../interfaces/IMatchDay";
import IRound from "../interfaces/IRound";

export type FixtureProps = {
  matchDay?: IMatchDay;
  round?: IRound;
  index: number;
};
