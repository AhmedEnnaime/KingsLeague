import { Disclosure } from "@headlessui/react";
import logo from "../assets/team_logo.png";
import { MatchupProps } from "../propsTypes/MatchupProps";
import Button from "../shared/Button";
import { useState } from "react";
import IMatch from "../interfaces/IMatch";
import ResultModal from "./ResultModal";

const Matchup = ({ matches }: MatchupProps) => {
  const [selectedMatch, setSelectedMatch] = useState<IMatch>();
  const [open, setOpen] = useState(false);
  return (
    <>
      <Disclosure.Panel className="px-4 pb-2 pt-4 text-sm text-gray-500">
        {matches &&
          matches.map((match) => (
            <div
              key={match.id}
              className="flex justify-between items-center gap-4 p-4"
            >
              <div className="flex items-center gap-4 p-2">
                <img
                  alt="Bonnie image"
                  height="96"
                  src={logo}
                  width="96"
                  className="mb-3 rounded-full shadow-lg"
                />
                <p className="text-xl font-bold">{match.teamA?.name}</p>
              </div>

              <div className="flex flex-col gap-4">
                <span className="text-xl font-bold">
                  {match.result != null ? match.result.score : match.time}
                </span>
                {match.result == null ? (
                  <Button
                    onClick={() => {
                      setSelectedMatch(match);
                      setOpen(true);
                    }}
                    content="Set Result"
                  />
                ) : (
                  ""
                )}
              </div>
              <div className="flex items-center gap-4 p-2">
                <img
                  alt="Bonnie image"
                  height="96"
                  src={logo}
                  width="96"
                  className="mb-3 rounded-full shadow-lg"
                />
                <p className="text-xl font-bold">{match.teamB?.name}</p>
              </div>
            </div>
          ))}
      </Disclosure.Panel>

      {open ? (
        <ResultModal open={open} setOpen={setOpen} match={selectedMatch} />
      ) : (
        ""
      )}
    </>
  );
};

export default Matchup;
