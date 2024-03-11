import { useEffect, useState } from "react";
import StadiumCard from "../components/StadiumCard";
import Button from "../shared/Button";
import Header from "../shared/Header";
import StadiumModal from "../components/StadiumModal";
import API from "../utils/API";
import IStadium from "../interfaces/IStadium";
import not_found from "../assets/not_found.png";

const Stadiums = () => {
  const [open, setOpen] = useState(false);
  const [stadiums, setStadiums] = useState<IStadium[]>();

  const fetchStadiums = async () => {
    await API.get(`/MATCH-SERVICE/api/v1/stadiums`)
      .then((res) => {
        setStadiums(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    fetchStadiums();
  }, [open]);
  return (
    <>
      <Header />
      <div className="flex justify-between p-4">
        <h1 className="text-2xl font-semibold">Stadiums</h1>
        <Button
          onClick={() => {
            setOpen(true);
          }}
          content="Create Stadium"
        />
      </div>

      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 justify-center pt-4 items-center">
        {stadiums ? (
          stadiums.map((stadium) => (
            <StadiumCard key={stadium.id} stadium={stadium} />
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
      {open ? <StadiumModal open={open} setOpen={setOpen} /> : ""}
    </>
  );
};

export default Stadiums;
