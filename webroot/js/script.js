/* 
 * Copyright 2020 Hampus Ram <hampus.ram@educ.goteborg.se>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

window.addEventListener('load', () => {
    // this is ugly code and you can do better!
    async function updateFortune() {
        let resp = await fetch('/api/fortune')
        let json = await resp.json()
        let fortune = document.getElementById('fortune')
        fortune.innerText = json.fortune
        fortune.style.visibility = 'visible';
    }
    
    let button = document.getElementById('mf');
    button.onclick = updateFortune;
    updateFortune()
})