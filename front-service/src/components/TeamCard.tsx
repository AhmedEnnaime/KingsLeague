import { useState } from "react";
import logo from "../assets/team_logo.png";
import { TeamCardProps } from "../propsTypes/TeamCardProps";
import { Card, Dropdown } from "flowbite-react";
import DeleteModal from "../shared/DeleteModal";
import TeamModal from "./TeamModal";
import TournamentsTeamModal from "./TournamentsTeamModal";
import { useNavigate } from "react-router-dom";

const TeamCard = ({ team }: TeamCardProps) => {
  const [openDelete, setOpenDelete] = useState(false);
  const [openUpdate, setOpenUpdate] = useState(false);
  const [openTournamentsModal, setOpenTournamentsModal] = useState(false);
  const navigate = useNavigate();
  return (
    <>
      <Card className="max-w-sm">
        <div className="flex justify-end px-4 pt-4">
          <Dropdown inline label="">
            <Dropdown.Item onClick={() => setOpenUpdate(true)}>
              <a
                href="#"
                className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-200 dark:hover:bg-gray-600 dark:hover:text-white"
              >
                Edit
              </a>
            </Dropdown.Item>
            <Dropdown.Item onClick={() => setOpenDelete(true)}>
              <a
                href="#"
                className="block px-4 py-2 text-sm text-red-600 hover:bg-gray-100 dark:text-gray-200 dark:hover:bg-gray-600 dark:hover:text-white"
              >
                Delete
              </a>
            </Dropdown.Item>
          </Dropdown>
        </div>
        <div className="flex flex-col items-center pb-10">
          <img
            alt="Bonnie image"
            height="96"
            src={logo}
            width="96"
            className="mb-3 rounded-full shadow-lg"
          />
          <h5 className="mb-1 text-xl font-medium text-gray-900 dark:text-white">
            {team.name}
          </h5>
          <span className="text-sm text-gray-500 dark:text-gray-400">
            {team.country}
          </span>
          <div className="mt-4 flex space-x-3 lg:mt-6">
            <span
              onClick={() => setOpenTournamentsModal(true)}
              className="inline-flex cursor-pointer items-center rounded-lg bg-cyan-700 px-4 py-2 text-center text-sm font-medium text-white hover:bg-cyan-800 focus:outline-none focus:ring-4 focus:ring-cyan-300 dark:bg-cyan-600 dark:hover:bg-cyan-700 dark:focus:ring-cyan-800"
            >
              Tournaments
            </span>

            <span
              onClick={() => navigate(`/team/${team.id}/players`)}
              className="inline-flex cursor-pointer items-center rounded-lg bg-cyan-700 px-4 py-2 text-center text-sm font-medium text-white hover:bg-cyan-800 focus:outline-none focus:ring-4 focus:ring-cyan-300 dark:bg-cyan-600 dark:hover:bg-cyan-700 dark:focus:ring-cyan-800"
            >
              Players
            </span>
          </div>
        </div>
      </Card>
      {openUpdate ? (
        <TeamModal open={openUpdate} setOpen={setOpenUpdate} team={team} />
      ) : (
        ""
      )}

      {openDelete ? (
        <DeleteModal open={openDelete} setOpen={setOpenDelete} element={team} />
      ) : (
        ""
      )}

      {openTournamentsModal ? (
        <TournamentsTeamModal
          open={openTournamentsModal}
          setOpen={setOpenTournamentsModal}
          team={team}
        />
      ) : (
        ""
      )}
    </>
  );
};

export default TeamCard;
