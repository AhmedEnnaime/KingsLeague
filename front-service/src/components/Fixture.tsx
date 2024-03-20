import { Disclosure } from "@headlessui/react";
import Matchup from "./Matchup";
import { ChevronUpIcon } from "@heroicons/react/20/solid";
import { FixtureProps } from "../propsTypes/FixtureProps";
import { RootState, useAppDispatch } from "../redux/store";
import { useSelector } from "react-redux";
import {
  fetchMatchesByMatchDayId,
  fetchMatchesByRoundId,
} from "../redux/matches/matchActions";
import { useEffect } from "react";

const Fixture = ({ matchDay, round, index }: FixtureProps) => {
  const dispatch = useAppDispatch();
  const matches = useSelector((state: RootState) => state.match.matches);
  const fetchMatches = () => {
    if (matchDay != null) {
      dispatch(fetchMatchesByMatchDayId(matchDay.id as number)).then((res) => {
        console.log(res.payload);
      });
    } else {
      dispatch(fetchMatchesByRoundId(round?.id as number));
    }
  };

  useEffect(() => {
    fetchMatches();
  }, []);
  return (
    <>
      <div className="w-full px-4">
        <div className="mx-auto w-full rounded-2xl bg-white p-2">
          <Disclosure>
            {({ open }) => (
              <>
                <Disclosure.Button className="flex w-full justify-between rounded-lg bg-blue-100 px-4 py-8 text-left text-sm font-medium text-blue-900 hover:bg-blue-200 focus:outline-none focus-visible:ring focus-visible:ring-blue-500/75">
                  <span className="text-xl font-bold">
                    {matchDay ? `MatchDay ${index + 1}` : `Round ${index + 1}`}
                  </span>
                  <p>{round ? round.date : matchDay?.date}</p>
                  <ChevronUpIcon
                    className={`${
                      open ? "rotate-180 transform" : ""
                    } h-5 w-5 text-vlue-500`}
                  />
                </Disclosure.Button>

                <Matchup matches={matches} />
              </>
            )}
          </Disclosure>
        </div>
      </div>
    </>
  );
};

export default Fixture;
