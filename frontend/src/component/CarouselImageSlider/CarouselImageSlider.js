import React from 'react';
import Carousel from "react-bootstrap/Carousel";
import {Link} from "react-router-dom";

const sliderItems = [
    {
        id: "98",
        name: "Photo 1",
        url: "https://scontent-hkg4-2.xx.fbcdn.net/v/t1.15752-9/280229456_314941990801973_6760223264104693241_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=XMpjm5pY6wgAX__y8dI&_nc_ht=scontent-hkg4-2.xx&oh=03_AVIqsED19ek8P8KNq606vpYgHcjc_mM4-cN4H5eI-ru01g&oe=62AA7534"
    },
    {
        id: "59",
        name: "Photo 2",
        url: "https://scontent-hkg4-2.xx.fbcdn.net/v/t1.15752-9/279232723_1343274689499577_160983726352559017_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=ygLvdsI8pF4AX9423QY&_nc_ht=scontent-hkg4-2.xx&oh=03_AVIYh0w6dGhnehCgvvRtBOu00Wom5WPHRDxHUZvH_EfTkw&oe=62AD5572"
    },
];

const CarouselImageSlider = () => {
    const settings = {
        indicators: false,
        fade: true,
        infinite: true,
        interval: 3000
    }

    return (
        <div>
            <Carousel {...settings}>
                {sliderItems.map((item, index) => {
                    return (
                        <Carousel.Item key={item.id}>
                            <Link to={`/product/${item.id}`}>
                                <img className="d-block w-100" src={item.url} alt={item.name}/>
                            </Link>
                        </Carousel.Item>
                    )
                })}
            </Carousel>
        </div>
    );
}

export default CarouselImageSlider;