import { Disclosure } from "@headlessui/react";
import Matchup from "./Matchup";
import { ChevronUpIcon } from "@heroicons/react/20/solid";
import { FixtureProps } from "../propsTypes/FixtureProps";
import IMatch from "../interfaces/IMatch";
import { useState } from "react";
import MatchModal from "./MatchModal";

const Fixture = ({ matchDay, round, index }: FixtureProps) => {
  const [open, setOpen] = useState(false);
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
                  <svg
                    onClick={() => setOpen(true)}
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    className="w-6 h-6"
                  >
                    <path
                      fillRule="evenodd"
                      d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25ZM12.75 9a.75.75 0 0 0-1.5 0v2.25H9a.75.75 0 0 0 0 1.5h2.25V15a.75.75 0 0 0 1.5 0v-2.25H15a.75.75 0 0 0 0-1.5h-2.25V9Z"
                      clipRule="evenodd"
                    />
                  </svg>

                  <ChevronUpIcon
                    className={`${
                      open ? "rotate-180 transform" : ""
                    } h-5 w-5 text-blue-500`}
                  />
                </Disclosure.Button>

                <Matchup matches={matchDay?.matches as IMatch[]} />
              </>
            )}
          </Disclosure>
        </div>
      </div>
      {open ? (
        <MatchModal
          open={open}
          setOpen={setOpen}
          fixture={matchDay ? matchDay : round}
        />
      ) : (
        ""
      )}
    </>
  );
};

export default Fixture;
