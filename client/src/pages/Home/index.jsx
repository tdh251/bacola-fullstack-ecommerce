import SafetyBanner from "./SafetyBanner";
import BestSellers from "./BestSellers";
import NewProducts from "./NewProducts";
import Slider from "./Slider";
import PromoBanners from "./PromoBanners";
import LatestNews from "./LatestNews";

function Home() {
   return (
      <>
         <Slider />
         <BestSellers />
         <SafetyBanner />
         <NewProducts />
         <PromoBanners />
         <LatestNews />
      </>
   );
}

export default Home;
