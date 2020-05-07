/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author dell
 */
public class PDF {

    public static void PDFwriter() throws DocumentException {
        try {
            String file_name = "C:\\wamp\\www\\Baskel\\PDF\\Conditions générales d'utilisation.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            Font Bold = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph par = new Paragraph("Conditions générales d'utilisation\n"
                    + "En vigueur au 07/05/2020\n"
                    + " \n", Bold);
            Paragraph par1 = new Paragraph("Les présentes conditions générales d'utilisation (dites « CGU ») ont pour objet l'encadrement juridique des modalités de mise à disposition du site et des services par  et de définir les conditions d’accès et d’utilisation des services par « l'Utilisateur ».\n"
                    + "Les présentes CGU sont accessibles sur le site à la rubrique «CGU».\n"
                    + "Toute inscription ou utilisation de l application implique l'acceptation sans aucune réserve ni restriction des présentes CGU par l’utilisateur. Lors de l'inscription sur le site via le Formulaire d’inscription, chaque utilisateur accepte expressément les présentes CGU en cochant la case précédant le texte suivant : « Je reconnais avoir lu et compris les CGU et je les accepte ».\n"
                    + "En cas de non-acceptation des CGU stipulées dans le présent contrat, l'Utilisateur se doit de renoncer à l'accès des services proposés par le site.\n"
                    + "Baskel.tn se réserve le droit de modifier unilatéralement et à tout moment le contenu des présentes CGU.");

            Paragraph par2 = new Paragraph("Article 1 : Les mentions légales", Bold);

            Paragraph par3 = new Paragraph("L’édition et la direction de la publication du site Baskel.tn est assurée par Groupe PIDEV, domicilié tunis.\n"
                    + "Numéro de téléphone est +21650987439\n"
                    + "Adresse e-mail baskeltn395@gmail.com.\n"
                    + "L'hébergeur du site Baskel.tn est la société +21650987439, dont le siège social est situé au soukra tunis, avec le numéro de téléphone : +21650987439.");

            Paragraph par4 = new Paragraph("ARTICLE 2 : Accès a l application", Bold);

            Paragraph par5 = new Paragraph("L application Baskel.tn permet à l'Utilisateur un accès gratuit aux services suivants :\n"
                    + "L application internet propose les services suivants :\n"
                    + "Cyclisme\n"
                    + "L applicationss est accessible gratuitement en tout lieu à tout Utilisateur ayant un accès à Internet. Tous les frais supportés par l'Utilisateur pour accéder au service (matériel informatique, logiciels, connexion Internet, etc.) sont à sa charge.\n"
                    + "L’Utilisateur non membre n'a pas accès aux services réservés. Pour cela, il doit s’inscrire en remplissant le formulaire. En acceptant de s’inscrire aux services réservés, l’Utilisateur membre s’engage à fournir des informations sincères et exactes concernant son état civil et ses coordonnées, notamment son adresse email.\n"
                    + "Pour accéder aux services, l’Utilisateur doit ensuite s'identifier à l'aide de son identifiant et de son mot de passe qui lui seront communiqués après son inscription.\n"
                    + "Tout Utilisateur membre régulièrement inscrit pourra également solliciter sa désinscription en se rendant à la page dédiée sur son espace personnel. Celle-ci sera effective dans un délai raisonnable.\n"
                    + "Tout événement dû à un cas de force majeure ayant pour conséquence un dysfonctionnement du site ou serveur et sous réserve de toute interruption ou modification en cas de maintenance, n'engage pas la responsabilité de Baskel.tn. Dans ces cas, l’Utilisateur accepte ainsi ne pas tenir rigueur à l’éditeur de toute interruption ou suspension de service, même sans préavis.\n"
                    + "L'Utilisateur a la possibilité de contacter le site par messagerie électronique à l’adresse email de l’éditeur communiqué à l’ARTICLE 1.");

            Paragraph par6 = new Paragraph("ARTICLE 3 : Collecte des données", Bold);

            Paragraph par7 = new Paragraph("Le site est exempté de déclaration à la Commission Nationale Informatique et Libertés (CNIL) dans la mesure où il ne collecte aucune donnée concernant les Utilisateurs.");

            Paragraph par8 = new Paragraph("ARTICLE 4 : Propriété intellectuelle", Bold);

            Paragraph par9 = new Paragraph("Les marques, logos, signes ainsi que tous les contenus du site (textes, images, son…) font l'objet d'une protection par le Code de la propriété intellectuelle et plus particulièrement par le droit d'auteur.\n"
                    + "L'Utilisateur doit solliciter l'autorisation préalable du site pour toute reproduction, publication, copie des différents contenus. Il s'engage à une utilisation des contenus du site dans un cadre strictement privé, toute utilisation à des fins commerciales et publicitaires est strictement interdite.\n"
                    + "Toute représentation totale ou partielle de ce site par quelque procédé que ce soit, sans l’autorisation expresse de l’exploitant du site Internet constituerait une contrefaçon sanctionnée par l’article L 335-2 et suivants du Code de la propriété intellectuelle.\n"
                    + "Il est rappelé conformément à l’article L122-5 du Code de propriété intellectuelle que l’Utilisateur qui reproduit, copie ou publie le contenu protégé doit citer l’auteur et sa source.");

            Paragraph par10 = new Paragraph("ARTICLE 5 : Responsabilité", Bold);

            Paragraph par11 = new Paragraph("Les sources des informations diffusées sur le site Baskel.tn sont réputées fiables mais le site ne garantit pas qu’il soit exempt de défauts, d’erreurs ou d’omissions.\n"
                    + "Les informations communiquées sont présentées à titre indicatif et général sans valeur contractuelle. Malgré des mises à jour régulières, le site Baskel.tn ne peut être tenu responsable de la modification des dispositions administratives et juridiques survenant après la publication. De même, le site ne peut être tenue responsable de l’utilisation et de l’interprétation de l’information contenue dans ce site.\n"
                    + "L'Utilisateur s'assure de garder son mot de passe secret. Toute divulgation du mot de passe, quelle que soit sa forme, est interdite. Il assume les risques liés à l'utilisation de son identifiant et mot de passe. Le site décline toute responsabilité.\n"
                    + "Le site Baskel.tn ne peut être tenu pour responsable d’éventuels virus qui pourraient infecter l’ordinateur ou tout matériel informatique de l’Internaute, suite à une utilisation, à l’accès, ou au téléchargement provenant de ce site.\n"
                    + "La responsabilité du site ne peut être engagée en cas de force majeure ou du fait imprévisible et insurmontable d'un tiers.");

            Paragraph par12 = new Paragraph("ARTICLE 6 : Publication par l’Utilisateur", Bold);

            Paragraph par13 = new Paragraph("Le site permet aux membres de publier les contenus suivants :\n"
                    + "annonces , événement ....\n"
                    + "Dans ses publications, le membre s’engage à respecter les règles de la Netiquette (règles de bonne conduite de l’internet) et les règles de droit en vigueur.\n"
                    + "Le site peut exercer une modération sur les publications et se réserve le droit de refuser leur mise en ligne, sans avoir à s’en justifier auprès du membre.\n"
                    + "Le membre reste titulaire de l’intégralité de ses droits de propriété intellectuelle. Mais en publiant une publication sur le site, il cède à la société éditrice le droit non exclusif et gratuit de représenter, reproduire, adapter, modifier, diffuser et distribuer sa publication, directement ou par un tiers autorisé, dans le monde entier, sur tout support (numérique ou physique), pour la durée de la propriété intellectuelle. Le Membre cède notamment le droit d'utiliser sa publication sur internet et sur les réseaux de téléphonie mobile.\n"
                    + "La société éditrice s'engage à faire figurer le nom du membre à proximité de chaque utilisation de sa publication.\n"
                    + "Tout contenu mis en ligne par l'Utilisateur est de sa seule responsabilité. L'Utilisateur s'engage à ne pas mettre en ligne de contenus pouvant porter atteinte aux intérêts de tierces personnes. Tout recours en justice engagé par un tiers lésé contre le site sera pris en charge par l'Utilisateur.\n"
                    + "Le contenu de l'Utilisateur peut être à tout moment et pour n'importe quelle raison supprimé ou modifié par le site, sans préavis.\n"
                    + " \n"
                    + ".");

            document.add(par);
            document.add(par2);
            document.add(par3);
            document.add(par4);
            document.add(par5);
            document.add(par6);
            document.add(par7);
            document.add(par8);
            document.add(par9);
            document.add(par10);
            document.add(par11);
            document.add(par12);
            document.add(par13);

            document.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public static void pdfRead() {
        try {
            Desktop.getDesktop().open(new File("C:\\wamp\\www\\Baskel\\PDF\\Conditions générales d'utilisation.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
