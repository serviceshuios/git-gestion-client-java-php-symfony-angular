<?php

namespace NMC\GestionClient\SF2\SimpleBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Bondecommande
 *
 * @ORM\Table(name="BonDeCommande", uniqueConstraints={@ORM\UniqueConstraint(name="id", columns={"id"})}, indexes={@ORM\Index(name="Idx_expiration", columns={"date", "delais"}), @ORM\Index(name="client", columns={"client"})})
 * @ORM\Entity
 */
class Bondecommande
{
    /**
     * @var string
     *
     * @ORM\Column(name="id", type="string", length=54, nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="commentaire", type="text", nullable=true)
     */
    private $commentaire;

    /**
     * @var string
     *
     * @ORM\Column(name="montant", type="decimal", precision=6, scale=2, nullable=false)
     */
    private $montant = '0.00';

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var integer
     *
     * @ORM\Column(name="delais", type="smallint", nullable=false)
     */
    private $delais = '0';

    /**
     * @var \Client
     *
     * @ORM\ManyToOne(targetEntity="Client")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="client", referencedColumnName="login")
     * })
     */
    private $client;



    /**
     * Get id
     *
     * @return string 
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set commentaire
     *
     * @param string $commentaire
     * @return Bondecommande
     */
    public function setCommentaire($commentaire)
    {
        $this->commentaire = $commentaire;

        return $this;
    }

    /**
     * Get commentaire
     *
     * @return string 
     */
    public function getCommentaire()
    {
        return $this->commentaire;
    }

    /**
     * Set montant
     *
     * @param string $montant
     * @return Bondecommande
     */
    public function setMontant($montant)
    {
        $this->montant = $montant;

        return $this;
    }

    /**
     * Get montant
     *
     * @return string 
     */
    public function getMontant()
    {
        return $this->montant;
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     * @return Bondecommande
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime 
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Set delais
     *
     * @param integer $delais
     * @return Bondecommande
     */
    public function setDelais($delais)
    {
        $this->delais = $delais;

        return $this;
    }

    /**
     * Get delais
     *
     * @return integer 
     */
    public function getDelais()
    {
        return $this->delais;
    }

    /**
     * Set client
     *
     * @param \NMC\GestionClient\SF2\SimpleBundle\Entity\Client $client
     * @return Bondecommande
     */
    public function setClient(\NMC\GestionClient\SF2\SimpleBundle\Entity\Client $client = null)
    {
        $this->client = $client;

        return $this;
    }

    /**
     * Get client
     *
     * @return \NMC\GestionClient\SF2\SimpleBundle\Entity\Client 
     */
    public function getClient()
    {
        return $this->client;
    }
}
