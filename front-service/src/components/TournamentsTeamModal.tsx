import { Modal } from "flowbite-react";
import { TournamentsTeamModalProps } from "../propsTypes/TournamentsTeamModal";
import { RootState, useAppDispatch } from "../redux/store";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { fetchTournamentTeamsByTeamId } from "../redux/tournamentTeams/tournamentTeamsActions";
import not_found from "../assets/not_found.png";

const TournamentsTeamModal = ({
  open,
  setOpen,
  team,
}: TournamentsTeamModalProps) => {
  const dispatch = useAppDispatch();
  const tournaments = useSelector(
    (state: RootState) => state.tournamentTeam.tournaments
  );

  useEffect(() => {
    dispatch(fetchTournamentTeamsByTeamId(team?.id as number));
  }, []);
  return (
    <Modal show={open} onClose={() => setOpen(false)}>
      <Modal.Header>Tournaments</Modal.Header>
      <Modal.Body>
        <div className="flex flex-col gap-4 p-4">
          {tournaments.length > 0 ? (
            tournaments.map((tournament) => (
              <div key={tournament.id} className="flex justify-between">
                <h2>{tournament.name}</h2>
                <span>{tournament.location}</span>
              </div>
            ))
          ) : (
            <div className="flex justify-center items-center lg:col-span-3">
              <img
                className="flex justify-center w-22 h-22"
                src={not_found}
                alt="not found"
              />
            </div>
          )}
        </div>
      </Modal.Body>
    </Modal>
  );
};

export default TournamentsTeamModal;
