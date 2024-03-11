import StadiumCard from "../components/StadiumCard";
import Header from "../shared/Header";

const Stadiums = () => {
  return (
    <>
      <Header />
      <div className="flex justify-between p-4">
        <h1 className="text-2xl font-semibold">Stadiums</h1>
      </div>

      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 justify-center pt-4 items-center">
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
      </div>
    </>
  );
};

export default Stadiums;
