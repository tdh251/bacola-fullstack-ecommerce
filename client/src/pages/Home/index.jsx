import SafetyBanner from "./SafetyBanner";
import BestSellers from "./BestSellers";
import NewProducts from "./NewProducts";
import Slider from "./Slider";
import PromoBanners from "./PromoBanners";
import Blog from "./Blog";

function Home() {
   return (
      <>
         <Slider />
         <BestSellers />
         <SafetyBanner />
         <NewProducts />
         <PromoBanners />
         <Blog />
      </>
   );
}

export default Home;
