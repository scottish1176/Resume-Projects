<template>
  <body class="about-body">
    <i class="fab fa-linkedin"></i>
    <div class="guestHome">
      <router-link
        id="create-button-about"
        v-bind:to="{ name: 'register' }"
        style="text-decorations: none; color: inherit"
        >Create Account?</router-link
      >
    </div>
    <h1 class="Meet">Meet The Team</h1>
    <div class="container">
      <div class="Hassan">
        <img
          class="Hassan-img"
          src="https://media-exp2.licdn.com/dms/image/C4E03AQEo7ZGfto68ow/profile-displayphoto-shrink_800_800/0/1646599243047?e=1660176000&v=beta&t=jRjItklAdTcqJZid2BYY_suoXu7GZlkar2eWc8cciPY"
        /><span class="caption-hassan"><b>Hassan Momin</b></span>
        <p class="Hassan-about">
          My passion for programming was initially purely driven by my
          inquisitive nature. Coming from a background in business I'm hardwired
          to find the most efficient process to complete the task at hand. Time
          and time again technology but more precisely programming was the key
          solution. The first business I started faced inventory problems due to
          the speed at which the products would sell out, which led me to
          stumble across automated scripts. I then taught myself how to read and
          implement those scripts in order to continue being able to acquire
          inventory. Today I'm pursuing a career as a Software Developer to
          continue producing creative solutions for complex problems.
        </p>
        <a
          href="https://www.linkedin.com/in/hassan-momin01/"
          title="LinkedIn"
          class="btn btn-linkedin btn-lg LinkedIn-btn"
        >
          <i class="fa fa-linkedin" style="font-size: 48px"></i>
        </a>
      </div>

      <div class="Rafael">
        <a
          href="https://www.linkedin.com/in/rafael-mosqueda32/"
          title="LinkedIn"
          class="btn btn-linkedin btn-lg LinkedIn-btn"
        >
          <i class="fa fa-linkedin" style="font-size: 48px"></i>
        </a>

        <p class="Rafael-about">
          I have always been a curious person and had a futuristic view. I love
          to ponder on what new wonders humanity can make in the future and how
          we can achieve such ambitious goals. I pursued a B.S in Mechanical
          Engineering for that purpose to see what new solutions I can create,
          but my curiosity has also led me to pursue coding and jump into the
          tech field to tackle bigger problems. I have joined the Java coding
          boot camp at TechElevator to help me learn about becoming a software
          developer to join the Tech Industry, help push humanity to the future,
          and have fun while coding. Coding is very challenging and rewarding
          which brings joy to me when I finally solve a problem and make my code
          run. I can't wait to collaborate with others to create unique
          solutions for the world.
        </p>
        <img
          class="Rafael-img"
          src="https://media-exp2.licdn.com/dms/image/C4D03AQGdSGbvhJxIAw/profile-displayphoto-shrink_800_800/0/1562868497120?e=1660176000&v=beta&t=1EwVqZ9nuPBhDUSvJuUKiyQ9vxToJb9n1Qfx8FzWS7g"
        /><span class="caption-rafael"><b>Rafael Mosqueda</b></span>
      </div>

      <div class="Will">
        <img
          class="Will-img"
          src="https://media-exp2.licdn.com/dms/image/C4D03AQHm3HpN3P45AQ/profile-displayphoto-shrink_800_800/0/1644854544998?e=1660176000&v=beta&t=f689BkaCR9o1hZVjd9apBOsY8e5xeH8K1Dnp9Yocfzo"
        /><span class="caption-will"><b>Will Scott</b></span>
        <p class="Will-about">
          After more than a decade of experience in the live entertainment
          production industry, I've decided to make a change. Mom and dad, I'm
          going to be a software developer!! I've thoroughly enjoyed learning so
          much about audio, video and lighting(AVL); from microphone
          transducers, delay compensation, sACN, and video codecs, to SQL, unit
          testing, HTML, Java and JS. It's very exciting to learn what happens
          to an AVL signal, on a software level, inside the components I've
          worked with over the years. I've found tremendous joy in learning new
          things, then applying them to the projects I'm working on. I can't
          wait to discover what I'll learn next and how it'll unlock a new
          feature in this ever-growing world of technology.
        </p>
        <a
          href="https://www.linkedin.com/in/developer-will-scott/"
          title="LinkedIn"
          class="btn btn-linkedin btn-lg LinkedIn-btn"
        >
          <i class="fa fa-linkedin" style="font-size: 48px"></i>
        </a>
      </div>

      <div class="Chris">
        <a
          href="https://www.linkedin.com/in/espinosachris/"
          title="LinkedIn"
          class="btn btn-linkedin btn-lg LinkedIn-btn"
        >
          <i class="fa fa-linkedin" style="font-size: 48px"></i>
        </a>

        <p>
          Throughout my life, I have always looked to expand my knowledge by
          learning challenging new skills. In the past three years, I have grown
          my communication and time-management skills working in a hospital
          setting making thousands of different medications. Though this was a
          great experience, I've always yearned for more. During my time off, I
          found a new passion for programming. This passion has led me to enroll
          in Tech Elevator's Java Development Bootcamp to grow my skills. I've
          always enjoyed input from others, coming up with plenty of new ideas
          for complex problems, and adapting to many situations. Once I
          graduate, I am looking for a software engineer role that allows me to
          continue my passions and enhance my coding skills.
        </p>
        <img
          class="Chris-img"
          src="https://media-exp2.licdn.com/dms/image/C4E03AQGgwdmZMshJuQ/profile-displayphoto-shrink_800_800/0/1646153239205?e=1660176000&v=beta&t=Oj_i7Vw27zMsqSKwiWwfNd4t_v2DLxiHMWLR4Z983bY"
        /><span class="caption-chris"><b>Chris Espinosa</b></span>
      </div>
    </div>
  </body>
</template>

<script>
export default {
  name: "about",
};
</script>

<style>
@import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css");

#create-button-about {
  position: absolute;
  padding: 20px;
  margin-left: 1637px;
  font-size: 25px;
  margin-top: -632px;
  color: rgb(0, 0, 0);
  border-bottom-left-radius: 30px;
  background-color: rgba(54, 148, 66, 0.397);
  white-space: nowrap;
  backdrop-filter: blur(5px);
  text-decoration: none;
}
#create-button-about:hover {
  background-color: #4e944f;
  transition-duration: 0.15s;
}
.container img {
  height: 200px;
  border-radius: 30px;
}
.Meet {
  font-size: 60px;
  position: absolute;
  margin-left: 713px;
}
.about-body {
  display: flex;
}
.container {
  margin-top: 130px;
  /* background-color: rgba(54, 148, 66, 0.397);  
  backdrop-filter: blur(10px); */
  padding: 30px;
}
.Hassan {
  display: flex;
  flex-direction: row;
  padding-right: 300px;
  padding-left: 300px;
  padding-bottom: 20px;
  align-items: center;
}
.Rafael {
  display: flex;
  flex-direction: row;
  padding-right: 300px;
  padding-left: 300px;
  padding-top: 20px;
  padding-bottom: 20px;
  align-items: center;
}
.Will {
  display: flex;
  flex-direction: row;
  padding-right: 300px;
  padding-left: 300px;
  padding-top: 20px;
  padding-bottom: 20px;
  align-items: center;
}
.Chris {
  display: flex;
  flex-direction: row;
  padding-right: 300px;
  padding-left: 300px;
  padding-top: 20px;
  align-items: center;
}
.btn-linkedin {
  background: #0e76a8;
  border-radius: 5px;
  color: #fff;
  border-width: 1px;
  border-style: solid;
  border-color: #084461;
}
.btn-linkedin:link,
.btn-linkedin:visited {
  color: #fff;
}
.btn-linkedin:active,
.btn-linkedin:hover {
  background: #084461;
  color: #fff;
}
.LinkedIn-btn {
  display: flex;
  flex-wrap: nowrap;
  align-items: center;
  height: 40px;
  width: 120px;
  padding: 10px;
}
.container p {
  margin-right: 30px;
  margin-left: 30px;
  background-color: rgba(54, 148, 67, 0.568);
  backdrop-filter: blur(20px);
  padding: 30px;
  border-radius: 80px;
  color: rgb(255, 255, 255);
}
.container a {
  text-decoration: none;
}
.caption-hassan {
  position: absolute;
  margin-top: 225px;
  margin-left: 38px;
}
.caption-will {
  position: absolute;
  margin-top: 225px;
  margin-left: 60px;
}
.caption-rafael {
  position: absolute;
  margin-top: 225px;
  margin-left: 1046px;
}
.caption-chris {
  position: absolute;
  margin-top: 225px;
  margin-left: 1057px;
}
.container span {
  background-color: rgba(54, 148, 67, 0.568);
  backdrop-filter: blur(20px);
  color: white;
  padding: 2px;
  border-radius: 5px;
}
</style>